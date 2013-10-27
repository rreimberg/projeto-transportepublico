using System;
using System.Collections.Generic;

using NHibernate;

namespace nanoit.sptrans.hackatona.core.domain.Abstract
{
	public interface IHibernateConnection : IConnection<ISession>
	{

	}

    public interface IHibernateConnectionStateless : IConnection<IStatelessSession>
    {

    }
}
