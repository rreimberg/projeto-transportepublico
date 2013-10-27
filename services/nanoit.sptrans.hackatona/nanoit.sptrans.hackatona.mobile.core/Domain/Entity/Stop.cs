using System.Collections.Generic;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    public class Stop
    {
        public virtual int Id { get; set; }
        public virtual string Name { get; set; }
        public virtual string Address { get; set; }
        public virtual int Number { get; set; }
        public virtual string Latitude { get; set; }
        public virtual string Longitude { get; set; }
        public virtual IList<StopLine> StopLines { get; set; }
    }
}
