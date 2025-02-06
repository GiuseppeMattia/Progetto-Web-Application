import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth.service';

export const venditoreGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService)
  const user = authService.currentUserValue;

  if(!user){
    return false;
  }

  const router=inject(Router)
  if (!user?.venditore){
    router.navigate(["/home"])
    return false;
  }

  return true;

};
