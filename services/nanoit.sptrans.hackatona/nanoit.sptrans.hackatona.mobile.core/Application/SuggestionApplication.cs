using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class SuggestionApplication
    {
        public Suggestion GetSuggestion(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Suggestion> hibernateRepository = new HibernateRepository<Suggestion>(hibernateConnection))
                {
                    var suggestion = hibernateRepository.FindOne(x => x.Id == pId);
                    return suggestion;
                }
            }
        }

        public bool SaveOrUpdateSuggestion(Suggestion pSuggestion)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Suggestion> hibernateRepository = new HibernateRepository<Suggestion>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pSuggestion);
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
