using Elmah;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;

namespace nanoit.sptrans.hackatona.core.domain.service
{
    public class Log : ILog
    {
        public void ExceptionLog(Exception ex)
        {
            if (HttpContext.Current != null)
            {
                ErrorSignal.FromCurrentContext().Raise(ex);
            }
            else
            {
                if (httpApplication == null) InitNoContext();
                ErrorSignal.Get(httpApplication).Raise(ex);
            }
        }

        private static HttpApplication httpApplication;
        private static readonly ErrorFilterConsole errorFilter = new ErrorFilterConsole();

        public static ErrorMailModule ErrorEmail = new ErrorMailModule();
        public static ErrorLogModule ErrorLog = new ErrorLogModule();
        public static ErrorTweetModule ErrorTweet = new ErrorTweetModule();

        private static void InitNoContext()
        {
            httpApplication = new HttpApplication();
            errorFilter.Init(httpApplication);

            (ErrorEmail as IHttpModule).Init(httpApplication);
            errorFilter.HookFiltering(ErrorEmail);

            (ErrorLog as IHttpModule).Init(httpApplication);
            errorFilter.HookFiltering(ErrorLog);

            (ErrorTweet as IHttpModule).Init(httpApplication);
            errorFilter.HookFiltering(ErrorTweet);
        }

        private class ErrorFilterConsole : ErrorFilterModule
        {
            public void HookFiltering(IExceptionFiltering module)
            {
                module.Filtering += base.OnErrorModuleFiltering;
            }
        }
    }
}
