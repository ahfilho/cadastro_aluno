package servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.model.Estudante;
import br.com.repository.Repository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class Servico {

	@Autowired
	private Repository repo;

	public Estudante findById(long id) throws ObjectNotFoundException {
		Optional<Estudante> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Id não encontrado!" + id + "TIPO:" + Servico.class.getName()));
	}
	
	public List<Estudante> todosDocumentos(){
		return  repo.findAll();
}
	 public Optional<Estudante> findStudentById(long id){
		  return repo.findById(id);
}
}