using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.application
{
    public class LineDetailApplication
    {
        public LineDetail GetLineDetail(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LineDetail> hibernateRepository = new HibernateRepository<LineDetail>(hibernateConnection))
                {
                    var lineDetail = hibernateRepository.FindOne(x => x.Id == pId);
                    return lineDetail;
                }
            }
        }

        public bool SaveOrUpdateLineDetail(LineDetail pLineDetail)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LineDetail> hibernateRepository = new HibernateRepository<LineDetail>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pLineDetail);
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
