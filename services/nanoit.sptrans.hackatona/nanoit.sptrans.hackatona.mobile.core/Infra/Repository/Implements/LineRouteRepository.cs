using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using nanoit.sptrans.hackatona.core.domain.entity;
using NHibernate.Criterion;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Implements
{
    public class LineRouteRepository : HibernateRepository<LineRoute>
    {
        public LineRouteRepository()
            : base(new HibernateConnection())
        {
        }

        public IEnumerable<LineRoute> FindManyLineRoute(string pOlhoVivoId)
        {
            return this.Connection.Connection.CreateCriteria<LineRoute>()
                .CreateAlias("Line", "Line")
                .Add(
                    Restrictions.Eq("Line.OlhoVivoId", pOlhoVivoId)).List<LineRoute>();
        }
    }
}
