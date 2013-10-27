using System;
using System.Security.Cryptography;
using System.Text;

namespace nanoit.sptrans.hackatona.core.domain.extensions
{
    public static class StringExtensions
    {
        public static string Cripto(this string value)
        {
            return GetMd5Hash(value);
        }

        private static string GetMd5Hash(string input)
        {
            var md5 = MD5.Create();
            var inputBytes = Encoding.ASCII.GetBytes(input);
            var hash = md5.ComputeHash(inputBytes);
            var sb = new StringBuilder();

            for (var i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("X2"));
            }

            return sb.ToString();
        }

        public static string ToWithoutAccents(this string text)
        {
            if (String.IsNullOrEmpty(text))
                return String.Empty;

            const string WithAccents = "ÄÅÁÂÀÃäáâàãÉÊËÈéêëèÍÎÏÌíîïìÖÓÔÒÕöóôòõÜÚÛüúûùÇç";
            const string WithOutAccents = "AAAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUuuuuCc";

            for (var i = 0; i < WithAccents.Length; i++)
                text = text.Replace(WithAccents[i].ToString(), WithOutAccents[i].ToString()).Trim();

            return text;
        }

        public static string ReplaceLabelBus(this string text)
        {
            text = text.ToUpper();
            text = text.Replace("JD ", "JARDIM ");
            text = text.Replace("JD.","JARDIM");
            text = text.Replace("TERM.","TERMINAL");
            text = text.Replace("PQ.","PARQUE");
		    text = text.Replace("PQ ","PARQUE ");
		    text = text.Replace("PQUE.","PARQUE");
		    text = text.Replace("D.PEDRO","DOM PEDRO");
		    text = text.Replace("D. PEDRO","DOM PEDRO");
		    text = text.Replace("HOSP.","HOSPITAL");
		    text = text.Replace("LGO.","LARGO");
		    text = text.Replace("LGO ","LARGO ");
		    text = text.Replace("PCA.","PRAÇA");
		    text = text.Replace("PÇA.","PRAÇA");
		    text = text.Replace("PCA ","PRAÇA ");
		    text = text.Replace("PÇA ","PRAÇA ");
		    text = text.Replace("PÇ ","PRAÇA ");
		    text = text.Replace("STA ","SANTA ");
		    text = text.Replace("STA.","SANTA");
		    text = text.Replace("V.","VILA");
		    text = text.Replace("VL.","VILA");
		    text = text.Replace("SHOP.","SHOPPING");
		    text = text.Replace("SHOP ","SHOPPING ");
		    text = text.Replace("STO ","SANTO ");
		    text = text.Replace("STO.","SANTO");
		    text = text.Replace("CID.","CIDADE");
		    text = text.Replace("EST.","ESTAÇÃO");
		    text = text.Replace("EST","ESTAÇÃO ");
		    text = text.Replace("(CIRC);","(CIRCULAR)");
		    text = text.Replace("CIRC.","CIRCULAR");
		    text = text.Replace("JR ","JUNIOR ");
		    text = text.Replace("JR.","JUNIOR");
		    text = text.Replace("PRINC.","PRINCESA");
		    text = text.Replace("CONJ ","CONJUNTO ");
		    text = text.Replace("CONJ.","CONJUNTO");
		    text = text.Replace("CJ ","CONJUNTO ");
		    text = text.Replace("CJ.","CONJUNTO");
		    text = text.Replace("CEM.","CEMITÉRIO");
		    text = text.Replace("CEMIT.","CEMITÉRIO");
		    text = text.Replace("CHAC ","CHACARA ");
		    text = text.Replace("CHAC.","CHACARA");
		    text = text.Replace("HAB.","HABITACIONAL");
		    text = text.Replace("NSA","NOSSA ");
		    text = text.Replace("NSA.","NOSSA");
		    text = text.Replace("SRA ","SENHORA ");
		    text = text.Replace("SRA.","SENHORA");
		    text = text.Replace("REC ","RECANTO ");
		    text = text.Replace("REC.","RECANTO");
		    text = text.Replace("RES.","RESIDENCIAL");
		    text = text.Replace("FCO.","FRANCISCO");        

            return text;
        }

        public static string ToCoordenate(this string text)
        {
            text = text.Replace(".", "").Replace(",", "");

            return text.Insert(3, ".");
        }
    }
}
