using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.Infra.Repository.Implements;
using System.Linq;

namespace nanoit.sptrans.hackatona.core.application
{
    public class LineRouteApplication
    {
        public LineRoute GetLineRoute(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LineRoute> hibernateRepository = new HibernateRepository<LineRoute>(hibernateConnection))
                {
                    var lineRoute = hibernateRepository.FindOne(x => x.Id == pId);
                    return lineRoute;
                }
            }
        }

        public bool SaveOrUpdateLineRoute(LineRoute pLineRoute)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LineRoute> hibernateRepository = new HibernateRepository<LineRoute>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pLineRoute);
                    if (insertEntity == null)
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }

        public IEnumerable<LineRoute> FindManyLineRoute(string pOlhoVivoId)
        {
            using (LineRouteRepository repository = new LineRouteRepository())
            {
                return repository.FindManyLineRoute(pOlhoVivoId).OrderBy(x => x.Sequence);
            }
        }
    }
}
