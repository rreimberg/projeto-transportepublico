using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class StopLineMap : ClassMap<StopLine>
    {
        public StopLineMap()
        {
            Table("tbCoO_LinhaParadaRelacionamento");
            CompositeId(x => x.Id)
                .KeyReference(x => x.Stop, "fk_int_parada")
                .KeyProperty(x => x.Prefix, "vch_prefixo")
                .KeyProperty(x => x.PrefixType, "vch_prefixoTipo")
                .KeyProperty(x => x.Direction, "int_Sentido");

        }
    }
}