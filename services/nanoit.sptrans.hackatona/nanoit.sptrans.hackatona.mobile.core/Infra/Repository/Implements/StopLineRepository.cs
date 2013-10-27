using System.Collections.Generic;
using NHibernate.Transform;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Implements
{
    public class StopLineRepository : HibernateRepository<StopLine>
    {
        public StopLineRepository()
            : base(new HibernateConnection())
        {
        }

        public IEnumerable<StopLineReturn> FindByStop(int stopId)
        {
            return Connection.Connection.CreateSQLQuery("exec prPesquisaLinhaPorParada :fk_int_parada")
                .SetParameter("fk_int_parada", stopId).SetResultTransformer(new AliasToBeanResultTransformer(typeof(StopLineReturn))).List<StopLineReturn>();
        }
    }
}