import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  constructor(private authService: AuthService, private route: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {



    const isAutenticado = this.authService.isAutenticado();
    //if (this.route.url.includes('/login') && isAuthenticade)
    //  this.route.navigate(['/']);

    if (isAutenticado) {

      console.info(state.url);
      /*if (state.url.includes('/login'))
        this.route.navigate(['/']);*/

      return true;
    }
    else {
      this.route.navigate(['/login']);
      return false;
    }
  }

}
