using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using NHibernate.Criterion;
using NHibernate;
using nanoit.sptrans.hackatona.core.domain.Abstract;

namespace nanoit.sptrans.hackatona.core.Infra.Repository
{
    public class HibernateRepository<T> : IHibernateRepository<T> where T : class, new()
    {
        public IConnection<ISession> Connection { get; private set; }

        public HibernateRepository(IHibernateConnection hibernateConnection)
        {
            this.Connection = hibernateConnection;
        }

        #region IRepository<T> Members

        public void Query(String sql)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.CreateSQLQuery(sql).ExecuteUpdate();
                
                tran.Commit();
            }
        }

        public T Save(T entity)
        {
            var session = Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Save(entity);
                    
                tran.Commit();
            }

            return entity;
        }

        public T SaveWithoutTransaction(T entity)
        {
            var session = Connection.Connection;

            session.SaveOrUpdate(entity);

            return entity;
        }

        public T Merge(T entity)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Merge(entity);

                tran.Commit();
            }

            return entity;
        }

        public T Update(T entity)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Update(entity);
                    
                tran.Commit();
            }

            return entity;
        }

        public T SaveOrUpdate(T entity)
        {
            var session = this.Connection.Connection;

                using (var tran = session.BeginTransaction())
                {
                    session.SaveOrUpdate(entity);

                    tran.Commit();
                }
            return entity;
        }

        public void SaveOrUpdateWithoutTransaction(T entity)
        {
            var session = this.Connection.Connection;

            session.SaveOrUpdate(entity);
        }

        public void Remove(T entity)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Delete(entity);

                tran.Commit();
            }
        }

        public T Get(int primaryKey)
        {
            var session = this.Connection.Connection;

            return session.Get<T>(primaryKey);
        }

        public T FindOne(Expression<Func<T, Boolean>> queryExpression)
        {
            var session = this.Connection.Connection;

            return session.QueryOver<T>()
                .Where(queryExpression)
                .List<T>()
                .FirstOrDefault<T>();
        }

        public IEnumerable<T> FindMany(Expression<Func<T, Boolean>> queryExpression)
        {
            var session = this.Connection.Connection;

            return session.QueryOver<T>()
                .Where(queryExpression)
                .List<T>();
        }

        public IEnumerable<T> FindMany(Expression<Func<T, Boolean>> queryExpression, Expression<Func<T, object>> lazyEntity)
        {
            var session = this.Connection.Connection;

            return session.QueryOver<T>()
                .Where(queryExpression)
                .Fetch(lazyEntity)
                .Eager()
                .List<T>();
        }

        public IEnumerable<T> List(Boolean isActive)
        {
            var session = this.Connection.Connection;

            return session.CreateCriteria<T>()
                .Add(Restrictions.Eq("IsActive", isActive))
                .List<T>();
        }

        public IEnumerable<T> List()
        {
            var session = this.Connection.Connection;

            return session.CreateCriteria<T>()
                .List<T>();
        }

        #endregion

        #region IDisposable Members

        public void Dispose()
        {
            if (this.Connection != null)
                this.Connection.Dispose();
        }

        #endregion
    }





    public class HibernateRepositoryStateless<T> : IHibernateRepositoryStateless<T> where T : class, new()
    {
        public IConnection<IStatelessSession> Connection { get; private set; }

        public HibernateRepositoryStateless(IHibernateConnectionStateless hibernateConnection)
        {
            this.Connection = hibernateConnection;
        }

        #region IRepository<T> Members

        public T Save(T entity)
        {
            var session = Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Insert(entity);

                tran.Commit();
            }

            return entity;
        }

        public T Merge(T entity)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                //session.Merge(entity);

                tran.Commit();
            }

            return entity;
        }

        public T Update(T entity)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Update(entity);

                tran.Commit();
            }

            return entity;
        }

        public T SaveOrUpdate(T entity)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Insert(entity);

                tran.Commit();
            }
            return entity;
        }

        public void SaveOrUpdateWithoutTransaction(Object entity)
        {
            var session = this.Connection.Connection;

            session.Insert(entity);
        }

        public void Remove(T entity)
        {
            var session = this.Connection.Connection;

            using (var tran = session.BeginTransaction())
            {
                session.Delete(entity);

                tran.Commit();
            }
        }

        public T Get(int primaryKey)
        {
            var session = this.Connection.Connection;

            return session.Get<T>(primaryKey);
        }

        public T FindOne(Expression<Func<T, Boolean>> queryExpression)
        {
            var session = this.Connection.Connection;

            return session.QueryOver<T>()
                .Where(queryExpression)
                .List<T>()
                .FirstOrDefault<T>();
        }

        public IEnumerable<T> FindMany(Expression<Func<T, Boolean>> queryExpression)
        {
            var session = this.Connection.Connection;

            return session.QueryOver<T>()
                .Where(queryExpression)
                .List<T>();
        }

        public IEnumerable<T> FindMany(Expression<Func<T, Boolean>> queryExpression, Expression<Func<T, object>> lazyEntity)
        {
            var session = this.Connection.Connection;

            return session.QueryOver<T>()
                .Where(queryExpression)
                .Fetch(lazyEntity)
                .Eager()
                .List<T>();
        }

        public IEnumerable<T> List(Boolean isActive)
        {
            var session = this.Connection.Connection;

            return session.CreateCriteria<T>()
                .Add(Restrictions.Eq("IsActive", isActive))
                .List<T>();
        }

        public IEnumerable<T> List()
        {
            var session = this.Connection.Connection;

            return session.CreateCriteria<T>()
                .List<T>();
        }

        #endregion

        #region IDisposable Members

        public void Dispose()
        {
            if (this.Connection != null)
                this.Connection.Dispose();
        }

        #endregion
    }
}
