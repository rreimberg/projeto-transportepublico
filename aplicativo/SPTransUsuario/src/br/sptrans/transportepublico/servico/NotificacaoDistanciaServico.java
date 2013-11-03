package br.sptrans.transportepublico.servico;

import java.util.ArrayList;
import java.util.List;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import br.sptrans.transportepublico.identificador.OnibusTipoIdentificador;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.modelo.OnibusProximoModelo;
import br.sptrans.transportepublico.usuario.OnibusActivity;
import br.sptrans.transportepublico.usuario.R;

public class NotificacaoDistanciaServico extends Service implements Runnable{

	private List<LinhaModelo> _linhaModelos = new ArrayList<LinhaModelo>();
	private int PONTO_SELECIONADO = 0;
	private CountDownTimer countDownTimer ;
	
	@Override
	public boolean stopService(Intent name) {
		Log.e("CoO", "AlguemEncerrouOServico");
		return super.stopService(name);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("CoO", "Criando...");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("CoO", "Ativo");
		recebeLinha(intent);
		criarNotificacao("Calculando ônibus mais próximo", "", "Ativando monitoração...",0);
		run();
		
		countDownTimer = new CountDownTimer(20000,1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				Log.e("CoO", String.valueOf(millisUntilFinished));
			}
			
			@Override
			public void onFinish() {
				Log.e("CoO", "CountDownTimerTerminou");
				run();
				start();
			}
			
			@Override
			protected void finalize() throws Throwable {
				super.finalize();
				Log.e("CoO", "CountDownTimerParou");
			}
		};
		
		countDownTimer.start();
		
		return Service.START_REDELIVER_INTENT;
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		_linhaModelos = null;
		NotificacaoCancelar(getApplicationContext());
		Log.e("CoO", "MORREU");
	}

	
	public static void NotificacaoCancelar(Context context)
	{
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancelAll();
	}
		
	private void criarNotificacao(String titulo,String texto,String ticket,int prefixo) {
	    Intent intent = abrirAtividadeMapaIntent(_linhaModelos);
	    intent.putExtra("servico", true);
	    intent.putExtra("monitorar", prefixo);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
	   
	    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(OnibusActivity.class);	
		stackBuilder.addNextIntent(intent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		 NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		 builder.setContentTitle(titulo)
	        .setContentText(texto)
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentIntent(pIntent)
	        .setTicker(ticket)
	        .setOnlyAlertOnce(true)
	        .setOngoing(true)
	        .setAutoCancel(true)
	        .setProgress(0, 0, true);
		 
		 builder.setContentIntent(resultPendingIntent);
		 NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		 mNotificationManager.notify(1, builder.build());
	  }
	

	private void recebeLinha(Intent intent)
	{
		if(intent != null)
		{
	        Bundle b = intent.getExtras();
	        String[] linhaIds = b.getString("linhaId").split(",");
	    	String[] prefixos = b.getString("prefixos").split(",");
	    	String[] tipos = b.getString("tipos").split(",");
	    	String[] empresas = b.getString("empresas").split(",");
	    	String[] sentidos = b.getString("sentidos").split(",");
	    	String[] denominacaoTPTS = b.getString("denominacaoTPTS").split(",");
	    	String[] denominacaoTSTP = b.getString("denominacaoTSTP").split(",");
	    	PONTO_SELECIONADO = b.getInt("pontoId");
	    	
	    	for (int i = 0; i < linhaIds.length; i++) 
			{
	    		LinhaModelo linhaModelo = new LinhaModelo();
	    		linhaModelo = new LinhaModelo();
	    		linhaModelo.setCodigoLinha(linhaIds[i]);
	    		linhaModelo.setLetreiro(prefixos[i]);
	    		linhaModelo.setTipo(tipos[i]);
	    		linhaModelo.setEmpresa(Integer.parseInt(empresas[i]));
	    		linhaModelo.setSentido(Integer.valueOf(sentidos[i]));
	    		linhaModelo.setDenominacaoTPTS(denominacaoTPTS[i]);
	    		linhaModelo.setDenominacaoTSTP(denominacaoTSTP[i]);
				_linhaModelos.add(linhaModelo);
			}	
		}
	}

	
	public Intent abrirAtividadeMapaIntent(List<LinhaModelo> linhaModelos)
	{		
		String linhaIds = "";
		String prefixos = "";
		String tipos = "";
		String sentidos = "";
		String empresas = "";
		String denominacaoTPTS = "";
		String denominacaoTSTP = "";
		
		if(linhaModelos != null)
			for (LinhaModelo linha : linhaModelos) 
			{
				linhaIds += linha.getCodigoLinha() + ",";
				prefixos += linha.getLetreiro() + ",";
				sentidos += linha.getSentido() + ",";
				empresas += linha.getEmpresa() + ",";
				denominacaoTPTS += linha.getDenominacaoTPTS() + ",";
				denominacaoTSTP += linha.getDenominacaoTSTP() + ",";
				tipos += linha.getTipo() + ",";
			}
		
		Intent i = new Intent(getApplicationContext(),OnibusActivity.class);
		i.putExtra("linhaId",linhaIds);
		i.putExtra("prefixos",prefixos);
		i.putExtra("tipos",tipos);
		i.putExtra("sentidos",sentidos);
		i.putExtra("empresas", empresas);
		i.putExtra("denominacaoTPTS", denominacaoTPTS);
		i.putExtra("denominacaoTSTP", denominacaoTSTP);
		return i;
	}


	@Override
	public void run() {
		AsyncTask<Void, Void, OnibusProximoModelo> async = new AsyncTask<Void, Void, OnibusProximoModelo>()
				{
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				Log.e("CoO", "Pesquisando...");
			}

					@Override
					protected OnibusProximoModelo doInBackground(Void... params) {
						String ids = "";
						if(_linhaModelos != null)
						{
							for (LinhaModelo linhaModelo : _linhaModelos) {
								ids += linhaModelo.getCodigoLinha() + ",";
							}
						}
						return  new OnibusProximoServico(getApplicationContext()).pesquisaOnibusProximo(ids, PONTO_SELECIONADO);
					}
					
					@Override
							protected void onCancelled() {
								super.onCancelled();
								Log.e("CoO", "AsyncCancelado");
							}
					
					@Override
					protected void onPostExecute(OnibusProximoModelo result) {
						super.onPostExecute(result);
						if(result == null || result.getBusPrefix() == 0)
						{
							criarNotificacao(
									"Cadê o Ônibus?",
									"Parece que nenhum ônibus está próximo a você",
									"Ops!",0);
							return;
						}
						
						
						String onibusTipoIdentificador = OnibusTipoIdentificador.getNome(result.getBusType());
						
						criarNotificacao(String.format("%s - Prefixo: %s", result.getPrefix() + "-" + result.getPrefixType(),result.getBusPrefix()),
								String.format("Um ônibus do tipo '%s' esta á %s km(s)", onibusTipoIdentificador, result.getDistance()),
								"Monitoração ativada",result.getBusPrefix());
						
						if(Double.parseDouble(result.getDistance()) < 2.0)
						{
							Vibrator rr = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
					        rr.vibrate(2000);
						}
						
						return;
					}
			
				};
		async.execute();
	}
}
