using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Line
    {
        public virtual int Id { get; set; }

        public virtual string SPTransId { get; set; }

        [DataMember(Name = "CodigoLinha")]
        public virtual string OlhoVivoId { get; set; }

        [DataMember(Name="Letreiro")]
        public virtual string PrefixLabel { get; set; }

        [DataMember(Name = "Tipo")]
        public virtual int PrefixId { get; set; }

        [DataMember(Name = "DenominacaoTSTP")]
        public virtual string LabelSourceName { get; set; }

        [DataMember(Name = "DenominacaoTPTS")]
        public virtual string LabelTargetName { get; set; }

        [DataMember(Name = "Sentido")]
        public virtual int Direction { get; set; }

        [DataMember(Name = "Circular")]
        public virtual Boolean Circular { get; set; }
        
        [DataMember(Name = "Informacoes")]
        public virtual String Informacoes { get; set; }

        [DataMember(Name = "Empresa")]
        public virtual int Company { get; set; }

        public virtual DateTime DateTimeOfCreation { get; set; }

        public virtual DateTime DateTimeOfLastUpdate { get; set; }

        public virtual IList<Itinerary> Itineraries { get; set; }

        public virtual IList<LineRoute> Coordenates { get; set; }
        

        public virtual string PrefixFull { get; set; }
        public virtual string Target { get; set; }

    }
}
