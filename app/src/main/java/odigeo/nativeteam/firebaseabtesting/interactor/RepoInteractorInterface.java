package odigeo.nativeteam.firebaseabtesting.interactor;

import java.util.List;

import odigeo.nativeteam.firebaseabtesting.controller.Repo;
import rx.Observable;

/**
 * Created by daniel.morales on 15/11/16.
 */

public interface RepoInteractorInterface {
    Observable<List<Repo>> getListOfRepos();
}
