package br.com.suga.task;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class TaskApplication {

	public static void main(String... args) {
		Quarkus.run(args);
	}

}
