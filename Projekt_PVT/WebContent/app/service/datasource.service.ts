import {Injectable} from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {IDatasource} from '../interface/datasource';
import {DataSourceJson} from '../interface/datasource-json';
import {DataSourceSingleJson} from '../interface/datasource-single-json';
import {Menu} from '../interface/menu';


@Injectable()
export class DatasourceService {
    private url = 'http://localhost:8080/Proj/ServletTest?';
    //private url = 'http://rigel.se:8080/Bulle/ServletTest?';
    private menuUrl = 'http://localhost:8080/Proj/GraphChoiceJsonServlet';


    constructor(private http: Http) { }

    getData(sourceOne: Object, sourceTwo?: Object): Observable<IDatasource[]> {
        console.log(this.getUrl(sourceOne, sourceTwo));
        return this.http.get(this.getUrl(sourceOne, sourceTwo))
            .map((response: Response) => <IDatasource[]> response.json())
            .do(data => console.log('All: ' + this.url  +  JSON.stringify(data)))
            .catch(this.handleError);
    }
    
    getMenu(): Observable<Menu[]> {
        return this.http.get(this.menuUrl)
            .map((response: Response) => <Menu[]> response.json().data)
            .do(data => console.log('All: ' +  JSON.stringify(data)))
            .catch(this.handleError);

    }

    private handleError(error: Response) {
        console.log(error);
        return Observable.throw(error.json().error || 'Server error');
    }
    
    private getUrl(sourceOne: Object, sourceTwo: Object) {
        return (this.url + 'database1=' + sourceOne["database"] + '&value1=' + sourceOne["dataset"] +
        (sourceTwo? '&database2=' + sourceTwo["database"] + '&value2=' + sourceTwo["dataset"] : ""));
    }
}
