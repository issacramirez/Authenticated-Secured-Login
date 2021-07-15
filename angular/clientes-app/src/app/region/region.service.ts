import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Region } from "./region";


@Injectable({
    providedIn: 'root'
})
export class RegionService {

    private urlEndPoint: string = "http://localhost:8080/api/region";

    constructor (private http: HttpClient) {}

    getRegiones(): Observable<Region[]> {
        return this.http.get<Region[]>(this.urlEndPoint);
    }

}