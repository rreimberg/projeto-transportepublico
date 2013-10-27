using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class PlataformApplication
    {
        public Plataform GetPlataform(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Plataform> hibernateRepository = new HibernateRepository<Plataform>(hibernateConnection))
                {
                    var plataform = hibernateRepository.FindOne(x => x.Id == pId);
                    return plataform;
                }
            }
        }

        public bool SaveOrUpdatePlataform(Plataform pPlataform)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Plataform> hibernateRepository = new HibernateRepository<Plataform>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pPlataform);
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
