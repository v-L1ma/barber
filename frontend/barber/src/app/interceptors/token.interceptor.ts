import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem("token");

  if (token) {
    const reqWithHeader = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`)
    });
    return next(reqWithHeader);
  }

  return next(req);
};