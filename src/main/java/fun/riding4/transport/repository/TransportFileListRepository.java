package fun.riding4.transport.repository;

import fun.riding4.transport.model.TransportFileList;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportFileListRepository extends ReactiveMongoRepository<TransportFileList, String> {
}
