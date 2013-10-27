using System;
using System.Collections.Generic;

using NHibernate;

namespace nanoit.sptrans.hackatona.core.domain.Abstract
{
    public interface IHibernateRepository<T> : IRepository<T, ISession>
	{
        void Query(String sql);
	}

    public interface IHibernateRepositoryStateless<T> : IRepository<T, IStatelessSession>
    {

    }
}
