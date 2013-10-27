using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class BusTypeSuggestionApplication
    {
        public BusTypeSuggestion GetBusTypeSuggestion(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<BusTypeSuggestion> hibernateRepository = new HibernateRepository<BusTypeSuggestion>(hibernateConnection))
                {
                    var busTypeSuggestion = hibernateRepository.FindOne(x => x.Id == pId);
                    return busTypeSuggestion;
                }
            }
        }

        public bool SaveOrUpdateBusTypeSuggestion(BusTypeSuggestion pBusTypeSuggestion)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<BusTypeSuggestion> hibernateRepository = new HibernateRepository<BusTypeSuggestion>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pBusTypeSuggestion);
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
