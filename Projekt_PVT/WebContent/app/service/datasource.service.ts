import {Injectable} from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {IDatasource} from './interface/datasource';

@Injectable()
export class DatasourceService {
    private url = 'http://localhost:8080/Proj/ServletTest?';

    constructor(private http: Http) { }

    getData(sourceOne: string, sourceTwo: string): Observable<IDatasource[]> {
        return this.http.get(this.getUrl(sourceOne, sourceTwo))
            .map((response: Response) => <IDatasource[]> response.json().data)
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
    
    getUrl(sourceOne : string, sourceTwo : string) {
        return (this.url + 'datasource=' + sourceOne) + (sourceTwo? '&datasource=' + sourceTwo : '');
    }
}
