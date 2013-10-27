using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain;
using System.Linq;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class ApplicationApplication
    {
        public nanoit.sptrans.hackatona.core.domain.entity.Application GetApplication(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<nanoit.sptrans.hackatona.core.domain.entity.Application> hibernateRepository = new HibernateRepository<nanoit.sptrans.hackatona.core.domain.entity.Application>(hibernateConnection))
                {
                    var application = hibernateRepository.FindOne(x => x.Id == pId);
                    return application;
                }
            }
        }

        public IEnumerable<nanoit.sptrans.hackatona.core.domain.entity.Application> GetLastApplication()
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<nanoit.sptrans.hackatona.core.domain.entity.Application> hibernateRepository = new HibernateRepository<nanoit.sptrans.hackatona.core.domain.entity.Application>(hibernateConnection))
                {
                    var application = hibernateRepository.List();
                    return application;
                }
            }
        }

        public bool SaveOrUpdateApplication(nanoit.sptrans.hackatona.core.domain.entity.Application pApplication)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<nanoit.sptrans.hackatona.core.domain.entity.Application> hibernateRepository = new HibernateRepository<nanoit.sptrans.hackatona.core.domain.entity.Application>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pApplication);
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
    }
}
