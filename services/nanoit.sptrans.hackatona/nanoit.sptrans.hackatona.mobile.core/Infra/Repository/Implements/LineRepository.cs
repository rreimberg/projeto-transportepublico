using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using nanoit.sptrans.hackatona.core.domain.entity;
using NHibernate.Criterion;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Implements
{
    public class LineRepository : HibernateRepository<Line>
    {
        public LineRepository()
            : base (new HibernateConnection())
        {
        }

        public IEnumerable<Line> FindManyLine(string pLine)
        {
            return this.Connection.Connection.QueryOver<Line>()
                                  .Where(Restrictions.Or(Restrictions.Like("PrefixFull", pLine, MatchMode.Anywhere),
                                                         Restrictions.Like("Target", pLine, MatchMode.Anywhere)))
                                  .Take(RepositoryConstants.MAX_RECORDS_SEARCH)
                                  .List();
        }
    }
}
