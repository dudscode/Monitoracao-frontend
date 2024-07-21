import { ErrorHandler, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorMonitoringService implements ErrorHandler {

  constructor() { }

  handleError(error: any): void {
    console.error('Erro a Serem monitorados', error)
  }
  private eviarLogs(erro){
    

  }
}
