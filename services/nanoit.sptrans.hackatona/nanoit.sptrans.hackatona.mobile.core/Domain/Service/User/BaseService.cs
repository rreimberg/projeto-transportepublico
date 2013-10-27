using nanoit.sptrans.hackatona.core.domain.Abstract;

namespace nanoit.sptrans.hackatona.core.domain.service
{
    public abstract class BaseService<T> : IRepositoryCRUD<T>
    {
        protected readonly IRepositoryCRUD<T> _repository;

        protected BaseService(IRepositoryCRUD<T> repository)
        {
            _repository = repository;
        }

        public T Update(T entity)
        {
            _repository.Update(entity);

            return entity;
        }

        public T GetByID(int id)
        {
            return _repository.Get(id);
        }

        public T Save(T entity)
        {
            return _repository.Save(entity);
        }

        public T Merge(T entity)
        {
            return _repository.Merge(entity);
        }

        public T SaveOrUpdate(T entity)
        {
            return _repository.SaveOrUpdate(entity);
        }

        public void Remove(T entity)
        {
            _repository.Remove(entity);
        }

        public T Get(int primaryKey)
        {
            return _repository.Get(primaryKey);
        }

        public T FindOne(System.Linq.Expressions.Expression<System.Func<T, bool>> queryExpression)
        {
            return _repository.FindOne(queryExpression);
        }

        public System.Collections.Generic.IEnumerable<T> FindMany(System.Linq.Expressions.Expression<System.Func<T, bool>> queryExpression)
        {
            return _repository.FindMany(queryExpression);
        }

        public System.Collections.Generic.IEnumerable<T> List(bool isActive)
        {
            return _repository.List(isActive);
        }

        public System.Collections.Generic.IEnumerable<T> List()
        {
            return _repository.List();
        }


        public System.Collections.Generic.IEnumerable<T> FindMany(System.Linq.Expressions.Expression<System.Func<T, bool>> queryExpression, System.Linq.Expressions.Expression<System.Func<T, object>> lazyEntity)
        {
            return _repository.FindMany(queryExpression, lazyEntity);
        }
    }
}
