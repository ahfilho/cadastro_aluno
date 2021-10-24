package servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.model.Estudante;
import br.com.repository.Repository;
import javassist.tools.rmi.ObjectNotFoundException;

public class Servico {

	@Autowired 
	private Repository repo;
	
	public Estudante findById(long id) throws ObjectNotFoundException {
	Optional<Estudante> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado!"+id+"TIPO:"+Servico.class.getName()));
	
	}
}
