using System;
using System.Collections.Generic;
using System.Linq;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.domain.extensions;
using nanoit.sptrans.hackatona.core.Infra.Repository.Implements;

namespace nanoit.sptrans.hackatona.core.application
{
    public class LineApplication
    {
        public void DeleteAll(Line line)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<LineRoute> hibernateRepository = new HibernateRepository<LineRoute>(hibernateConnection))
                {
                    hibernateRepository.Query("delete from tbcoo_linharota where FK_int_Linha = " + line.Id.ToString());
                }

                using (HibernateRepository<Itinerary> hibernateRepository = new HibernateRepository<Itinerary>(hibernateConnection))
                {
                    hibernateRepository.Query("delete from tbcoo_itinerario where FK_int_Linha = " + line.Id.ToString());
                }

                using (HibernateRepository<Line> hibernateRepository = new HibernateRepository<Line>(hibernateConnection))
                {
                    hibernateRepository.Query("delete from tbcoo_Linha where PK_int_Linha = " + line.Id.ToString());
                }
            }
        }

        public Line GetLine(int pId)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Line> hibernateRepository = new HibernateRepository<Line>(hibernateConnection))
                {
                    var line = hibernateRepository.FindOne(x => x.Id == pId);
                    return line;
                }
            }
        }

        public IEnumerable<Line> List()
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Line> hibernateRepository = new HibernateRepository<Line>(hibernateConnection))
                {
                    return hibernateRepository.List();
                }
            }
        }

        public bool SaveOrUpdateLine(Line pLine)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Line> hibernateRepository = new HibernateRepository<Line>(hibernateConnection))
                {
                    var insertEntity = hibernateRepository.SaveOrUpdate(pLine);
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

        public IEnumerable<Line> FindManyLine(string pLine)
        {
            pLine = pLine.ToWithoutAccents().ReplaceLabelBus().Replace(" ", "%");

            using (LineRepository repository = new LineRepository())
            {
                return repository.FindManyLine(pLine);
            }

        }

        public bool DoBulkInsertStateless(IList<Line> pLines)
        {
            using (HibernateConnectionStateless hibernateConnection = new HibernateConnectionStateless())
            {
                using (HibernateRepositoryStateless<Line> hibernateRepository = new HibernateRepositoryStateless<Line>(hibernateConnection))
                {
                    hibernateConnection.Connection.SetBatchSize(pLines.Count);

                    foreach (var line in pLines)
                    {
                        hibernateRepository.SaveOrUpdateWithoutTransaction(line);
                    }


                    hibernateConnection.Connection.SetBatchSize(pLines.Sum(p => p.Itineraries.Count));
                    foreach (var line in pLines)
                    {
                        foreach (var it in line.Itineraries)
                        {
                            hibernateRepository.SaveOrUpdateWithoutTransaction(it);
                        }
                    }

                    hibernateConnection.Connection.SetBatchSize(pLines.Sum(p => p.Coordenates.Count));
                    foreach (var line in pLines)
                    {
                        foreach (var co in line.Coordenates)
                        {
                            hibernateRepository.SaveOrUpdateWithoutTransaction(co);
                        }
                    }

                    return true;
                }
            }


        }


        public bool DoBulkInsert(IList<Line> pLines)
        {
            using (HibernateConnection hibernateConnection = new HibernateConnection())
            {
                using (HibernateRepository<Line> hibernateRepository = new HibernateRepository<Line>(hibernateConnection))
                {
                    var qtd = pLines.Count + pLines.Sum(p => p.Itineraries.Count) + pLines.Sum(p => p.Coordenates.Count);
                    qtd = 1;

                    hibernateConnection.Connection.SetBatchSize(qtd);

                    foreach (var line in pLines)
                    {
                        hibernateRepository.SaveOrUpdate(line);
                    }

                    return true;
                }
            }
        }
    }
}
