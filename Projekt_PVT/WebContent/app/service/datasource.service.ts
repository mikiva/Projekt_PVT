import {Injectable} from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {IDatasource} from './interface/datasource';
import {DataSourceJson} from 'app/interface/datasource-json';
import {DataSourceSingleJson} from 'app/interface/datasource-single-json';

@Injectable()
export class DatasourceService {
    private url = 'http://localhost:8080/Proj/ServletTest?';
    //private url = 'http://rigel.se:8080/Bulle/ServletTest?';

    constructor(private http: Http) { }

    getData(sourceOne: string, sourceTwo: string): Observable<DataSourceJson | DataSourceSingleJson> {
        return this.http.get(this.getUrl(sourceOne, sourceTwo))
            .map((response: Response) => <DataSourceJson | DataSourceSingleJson> response.json())
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.log(error);
        return Observable.throw(error.json().error || 'Server error');
    }
    
    getUrl(sourceOne : string, sourceTwo : string) {
        return (this.url + 'datasource=' + sourceOne) + (sourceTwo? '&datasource=' + sourceTwo : '');
    }
}
