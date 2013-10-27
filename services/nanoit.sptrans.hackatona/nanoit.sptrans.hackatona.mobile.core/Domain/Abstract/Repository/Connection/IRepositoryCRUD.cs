using System;
using System.Collections.Generic;
using System.Linq.Expressions;

namespace nanoit.sptrans.hackatona.core.domain.Abstract
{
	public interface IRepositoryCRUD<Entity>
	{
        Entity Save(Entity entity);

        Entity Merge(Entity entity);

        Entity Update(Entity entity);

        Entity SaveOrUpdate(Entity entity);

        void Remove(Entity entity);

        Entity Get(int primaryKey);

        Entity FindOne(Expression<Func<Entity, Boolean>> queryExpression);

        IEnumerable<Entity> FindMany(Expression<Func<Entity, Boolean>> queryExpression);

        IEnumerable<Entity> FindMany(Expression<Func<Entity, Boolean>> queryExpression, Expression<Func<Entity, object>> lazyEntity);

        IEnumerable<Entity> List(Boolean isActive);

        IEnumerable<Entity> List();
	}
}
