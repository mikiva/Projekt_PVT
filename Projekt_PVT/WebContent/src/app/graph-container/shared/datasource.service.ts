import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {IDatasource} from './datasource';
import {DataSourceJson} from './datasource-json';
import {DataSourceSingleJson} from '../graph/datasource-single-json';
import {Menu} from '../choose-datasource/menu';


@Injectable()
export class DatasourceService {
    private url = 'http://rigel.hig.se:8080/Bulle-dev/ServletTest?';
    //private url = 'http://rigel.se:8080/Bulle/ServletTest?';
    private menuUrl = 'http://rigel.hig.se:8080/Bulle-dev/GraphChoiceJsonServlet?';
    
    


    constructor(private http: Http) { }
    
    getData(dateBefore: string, dateAfter: string, resolution: string, sourceOne: Object, sourceTwo?: Object): Observable<IDatasource[]> {
        return this.http.get(this.getUrl(sourceOne, sourceTwo, resolution, dateBefore, dateAfter))
            .map((response: Response) => <IDatasource[]> response.json())
            .catch(this.handleError);
    }
    
    getMenu(): Observable<Menu[]> {
        return this.http.get(this.menuUrl)
            .map((response: Response) => <Menu[]> response.json().data)
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.log(error);
        return Observable.throw(error.json().error || 'Server error');
    }

    getUrl(sourceOne: Object, sourceTwo: Object, resolution: string, dateBefore: string, dateAfter: string) {
        return (this.url + 'res=' + (resolution? resolution : 'day') + '&database1=' + sourceOne["database"] + '&value1=' + sourceOne["dataset"] +
        (sourceTwo? '&database2=' + sourceTwo["database"] + '&value2=' + sourceTwo["dataset"] : "")
        + '&startDate=' + (dateBefore || '0000-01-01') + '&endDate=' + (dateAfter || '9999-12-30'));
    } 
}
