import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  CanActivate,
  CanActivateChild,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
} from '@angular/router';
import { environment } from '../../environments/environment';
import { catchError, Observable, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {
  constructor(private router: Router, private httpCliente:HttpClient) {}

  private isAuthenticated(): Observable<boolean> {
    const token = localStorage.getItem('token');

    return this.httpCliente.get<boolean>(`${environment.apiUrl}/auth/validar-token`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
    }).pipe(catchError(()=> of(false)))
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Observable<boolean> {
    return this.isAuthenticated().pipe(
    tap(isAuth => {
      if (!isAuth) {
        this.router.navigate(['/login'], {
          state: {destino: state.url}
        });
        console.log(state.url)
      } 
    })
  );
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>  {
    return this.canActivate(childRoute, state);
  }
}
