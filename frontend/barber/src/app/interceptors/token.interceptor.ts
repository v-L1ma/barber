import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  
    const reqWithCredentials = req.clone({
      withCredentials: true
    });

    return next(reqWithCredentials);
};
