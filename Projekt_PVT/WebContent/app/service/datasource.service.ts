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

    getData(sourceOne: string, sourceTwo: string): Observable<IDatasource[]> {
        return this.http.get(this.getUrl(sourceOne, sourceTwo))
            .map((response: Response) => <IDatasource[]> response.json())
            .do(data => console.log('All: ' +  JSON.stringify(data)))
            .catch(this.handleError);
    }
    
    getMenu(): Observable<Menu[]> {
        return this.http.get(this.menuUrl)
            .map((response: Response) => <Menu[]> response.json())
            .do(data => console.log('All: ' +  JSON.stringify(data)))
            .catch(this.handleError);

    }

    private handleError(error: Response) {
        console.log(error);
        return Observable.throw(error.json().error || 'Server error');
    }
    
    getUrl(sourceOne : string, sourceTwo : string) {
        //return (this.url + 'datasource=' + sourceOne) + (sourceTwo? '&datasource=' + sourceTwo : '');
        return (this.url + 'datasource=quandl&database1=ODA&values1=SWE_LE&datasource=quandl&database2=ODA&values2=PBANSOP_USD');
    }
}
