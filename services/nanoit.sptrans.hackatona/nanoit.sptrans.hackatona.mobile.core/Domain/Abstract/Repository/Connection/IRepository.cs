using System;
using System.Collections.Generic;

using System.Linq.Expressions;

namespace nanoit.sptrans.hackatona.core.domain.Abstract
{
    public interface IRepository<Entity, Con> : IRepositoryCRUD<Entity>, IDisposable
	{
        IConnection<Con> Connection { get; }
	}
}
