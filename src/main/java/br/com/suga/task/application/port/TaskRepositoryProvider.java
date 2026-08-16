package br.com.suga.task.application.port;

import br.com.suga.task.application.usecase.StorageMode;
import br.com.suga.task.domain.repository.TaskRepository;

public interface TaskRepositoryProvider {
    TaskRepository resolve(StorageMode mode);
}
