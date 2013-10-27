using System;
using System.Collections.Generic;
using nanoit.sptrans.hackatona.core.domain;
using System.Linq;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.domain.extensions;

namespace nanoit.sptrans.hackatona.core.application
{
    public class ApplicationWarningApplication
    {
        public ApplicationWarning GetApplicationWarning(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<ApplicationWarning> hibernateRepository = new HibernateRepository<ApplicationWarning>(hibernateConnection))
                {
                    var applicationWarning = hibernateRepository.FindMany(x => x.Application.Id == pId &&
                                                                               x.DateTimeOfStart <= DateTimeExtensions.UTCBrazilDateTime &&
                                                                               x.DateTimeOfEnd >= DateTimeExtensions.UTCBrazilDateTime)
                                                                .OrderBy(x => x.DateTimeOfCreation).FirstOrDefault();
                    return applicationWarning;
                }
            }
        }

        public ApplicationWarning GetLastApplicationWarning(string pMessage, int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<ApplicationWarning> hibernateRepository = new HibernateRepository<ApplicationWarning>(hibernateConnection))
                {
                    var applicationWarning = hibernateRepository.FindMany(x => x.Message == pMessage && x.Application.Id == pId)
                                                                .OrderBy(x => x.DateTimeOfCreation)
                                                                .LastOrDefault();
                    return applicationWarning;
                }
            }
        }

        public bool SaveOrUpdateApplicationWarning(ApplicationWarning pApplicationWarning)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<ApplicationWarning> hibernateRepository = new HibernateRepository<ApplicationWarning>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pApplicationWarning);
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

        public void ChecarPosicaoOnibusLinha(Boolean isSpTransServiceOk)
        {
            ApplicationApplication application = new ApplicationApplication();

            String warningMessage  = "O serviço da SpTrans está com instabilidade no momento. O acompanhamento de ônibus pode não funcionar como esperado.";
            DateTime brazilTimeNow = DateTimeExtensions.UTCBrazilDateTime;

            IEnumerable<nanoit.sptrans.hackatona.core.domain.entity.Application> listApplicationEntity = application.GetLastApplication();


            foreach (var applicationEntity in listApplicationEntity)
            {
                var warningEntity = this.GetLastApplicationWarning(warningMessage, applicationEntity.Id);

                if (warningEntity == null)
                {
                    warningEntity = new ApplicationWarning
                    {
                        Application = applicationEntity,
                        DateTimeOfCreation = brazilTimeNow,
                        Message = warningMessage
                    };
                }


                if (isSpTransServiceOk)
                {
                    warningEntity.DateTimeOfStart = brazilTimeNow.AddMinutes(-2);
                    warningEntity.DateTimeOfEnd = brazilTimeNow.AddMinutes(-1);
                }
                else
                {
                    warningEntity.DateTimeOfStart = brazilTimeNow;
                    warningEntity.DateTimeOfEnd = brazilTimeNow.AddMinutes(30);
                }

                this.SaveOrUpdateApplicationWarning(warningEntity);
            }
        }
    }
}
