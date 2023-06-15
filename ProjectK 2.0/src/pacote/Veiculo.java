package pacote;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import conexao.Conexao;

public class Veiculo {
	
	
	private String marcaV;
	private String modeloV;
	private int anoV; 
	private int pneusE;
	private int oleoE;
	private int motorE;
	private int bateriaE;
	private boolean fixedV;
	private int idV;
	
	//GETTERS
	
	public String getMarcaV() {
		return marcaV;
	}
	public String getModeloV() {
		return modeloV;
	}
	public int getAnoV() {
		return anoV;
	}
	public int getPneusE() {
		return pneusE;
	}
	public int getOleoE() {
		return oleoE;
	}
	public int getMotorE() {
		return motorE;
	}
	public int getBateriaE() {
		return bateriaE;
	}
	public boolean getFixedV() {
		return fixedV;
	}
	public int getIdV() {
		return idV;
	}
	//SETTERS
	
	public void setPneusE(int pneusE) {
		this.pneusE = pneusE;
	}
	public void setOleoE(int oleoE) {
		this.oleoE = oleoE;
	}
	public void setMotorE(int motorE) {
		this.motorE = motorE;
	}
	public void setBateriaE(int bateriaE) {
		this.bateriaE = bateriaE;
	}
	public void setFixedV(boolean fixedV) {
		this.fixedV = fixedV;
	}
	
	//METHODS
	
	public static void registrarRevisao(){
		Random generator = new Random();
		Scanner ler = new Scanner(System.in);
		System.out.println("Marca do carro:");
		String marcV = ler.next();
		System.out.println("Modelo do carro:");
		String modelV = ler.next();
		System.out.println("Ano do carro:");
		int anoVV = ler.nextInt();
		System.out.println("Estado dos pneus (de 1 a 10):");
		int estadoP = ler.nextInt();
		System.out.println("Tempo sem trocar óleo (em mêses):");
		int tempoO = ler.nextInt();
		System.out.println("Estado do motor(de 1 a 10):");
		int estadoM = ler.nextInt();
		System.out.println("Carga da bateria(de 1 a 100):");
		int cargaV = ler.nextInt();
		int idV = generator.nextInt(100);
		
		PreparedStatement ps = null;
		Statement st = null;
		String print = "INSERT INTO veiculos(id,marca,modelo,ano,estadopneu,tempooleo,estadomotor,cargabateria) VALUES (?,?,?,?,?,?,?,?)";
		try {
			ps = Conexao.getConexao().prepareStatement(print);
			ps.setInt(1,idV);
			ps.setString(2, marcV);
			ps.setString(3, modelV);
			ps.setInt(4,anoVV);
			ps.setInt(5,estadoP);
			ps.setInt(6,tempoO);
			ps.setInt(7,estadoM);
			ps.setInt(8,cargaV);
			ps.execute();
			ps.close();
		}
		catch (SQLException e){
			System.out.println("kk");
			e.printStackTrace();}
		
	}
	
	public static void obterDados() {
		
		PreparedStatement ps = null;
		Statement st = null;
		
		try {
			st = Conexao.getConexao().createStatement();
			ResultSet print = st.executeQuery("SELECT * FROM veiculos");
			int index = 1;
			while(print.next()){
				
					System.out.println("Veículo "+index);
			      System.out.println("Id: "+print.getString(1));
			      System.out.println("Marca: "+print.getString(2));
			      System.out.println("Modelo: "+print.getString(3));
			      System.out.println("Ano: "+print.getString(4));
			      System.out.println("Estado dos pneus: "+print.getString(5)+"/10");
			      System.out.println("Tempo de óleo: "+print.getString(6)+" anos");
			      System.out.println("Estado do motor: "+print.getString(7)+"/10");
			      System.out.println("Carga da bateria: "+print.getString(8)+"%");
			      System.out.println(" ");
			      index++;
			   }
			st.close();
		      print.close();
		}
		catch (SQLException e){
			System.out.println("kk");
			e.printStackTrace();
		}
		
		
		/* System.out.println("      ");
		System.out.println("ID: "+this.getIdV());
		System.out.println("Marca: "+this.getMarcaV());
		System.out.println("Modelo: "+this.getModeloV());
		System.out.println("Ano: "+this.getAnoV());
		System.out.println("Estado dos pneus: "+this.getPneusE()+"/10");
		System.out.println("Tempo do óleo: "+this.getOleoE()+" anos");
		System.out.println("Estado do motor: "+this.getMotorE()+"/10");
		System.out.println("Carga da bateria: "+this.getBateriaE()+"%");
		if(fixedV==false) {
			System.out.println("Não foi reparado ainda.");
		}else { System.out.println("Veículo reparado."); }
		System.out.println("      "); */
}
	public static void reparar(int id) {
		
		PreparedStatement ps = null;
		Statement st = null;
		String print1 = "update veiculos set estadopneu = 10 where id=? and estadopneu<6";
		String print2 = "update veiculos set tempooleo = 0 where id=? and tempooleo>3";
		String print3 = "update veiculos set estadomotor = 10 where id=? and estadomotor<6";
		String print4 = "update veiculos set cargabateria = 100 where id=? and cargabateria<25";
		
		try {
			ps = Conexao.getConexao().prepareStatement(print1);
			ps.setInt(1, id);
			ps = Conexao.getConexao().prepareStatement(print2);
			ps.setInt(1, id);
			ps = Conexao.getConexao().prepareStatement(print3);
			ps.setInt(1, id);
			ps = Conexao.getConexao().prepareStatement(print4);
			ps.setInt(1, id);
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e){
			System.out.println("kk");
			e.printStackTrace();
		}
		
	}
	public static void removerVeiculo(int id){
		
		PreparedStatement ps = null;
		Statement st = null;
		
		try {
			String print = "delete from veiculos where id = ?";
			ps = Conexao.getConexao().prepareStatement(print);
			ps.setInt(1, id);
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e){
			System.out.println("kk");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
}