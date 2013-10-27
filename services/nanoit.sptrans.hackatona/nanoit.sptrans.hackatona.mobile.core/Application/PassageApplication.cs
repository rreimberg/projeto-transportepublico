using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class PassageApplication
    {
        public IEnumerable<Passage> ListPassage()
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Passage> hibernateRepository = new HibernateRepository<Passage>(hibernateConnection))
                {
                    var passage = hibernateRepository.List();
                    return passage;
                }
            }
        }

        public Passage GetPassage(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Passage> hibernateRepository = new HibernateRepository<Passage>(hibernateConnection))
                {
                    var passage = hibernateRepository.FindOne(x => x.Id == pId);
                    return passage;
                }
            }
        }

        public bool SaveOrUpdatePassage(Passage pPassage)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Passage> hibernateRepository = new HibernateRepository<Passage>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pPassage);
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
