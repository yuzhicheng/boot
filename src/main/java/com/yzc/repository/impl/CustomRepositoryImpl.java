package com.yzc.repository.impl;

import static com.google.common.collect.Iterables.toArray;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.yzc.repository.CustomRepository;

public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements CustomRepository<T, ID> {

	private final EntityManager entityManager;

	public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Page<T> findByAuto(T example, Pageable pageable) {

		return findAll(byAuto(entityManager, example), pageable);
	}

	@SuppressWarnings({ "unchecked" })
	public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {

		final Class<T> type = (Class<T>) example.getClass();

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<Predicate>();
				EntityType<T> entity = entityManager.getMetamodel().entity(type);
				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {

					Object attrValue = getValue(example, attr);
					if (attrValue != null) {
						if (attr.getJavaType() == String.class) {
							if (!StringUtils.isEmpty(attrValue)) {
								predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),
										pattern((String) attrValue)));
							}
						} else {
							predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
									attrValue));
						}
					}
				}
				return predicates.isEmpty() ? cb.conjunction() : cb.and(toArray(predicates, Predicate.class));
			}

			/**
			 * 获得实体类的当前属性的SingularAttribute，SingularAttribute包含的是实体类的某个单独属性
			 */
			@SuppressWarnings("hiding")
			private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
					Class<E> fieldClass) {

				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}

			/**
			 * 通过反射获得实体类对象对应属性的属性值
			 */
			@SuppressWarnings("hiding")
			private <T> Object getValue(T example, Attribute<T, ?> attr) {

				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}

		};
	}

	/**
	 * 构造like查询模式，进行模糊查询
	 */
	static private String pattern(String str) {
		return "%" + str + "%";
	}

}
