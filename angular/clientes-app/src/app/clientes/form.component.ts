import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import swal from 'sweetalert2';
import { RegionService } from '../region/region.service';
import { Region } from '../region/region';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public cliente: Cliente = new Cliente();
  public titulo: string = 'Crear Cliente';
  public regiones: Region[];
  public errores: string[] | any;

  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute, private regionService: RegionService) { }

  ngOnInit(): void {
    this.cargarCliente()
    this.regionService.getRegiones().subscribe(regiones => this.regiones = regiones)
  }

  public create(): void {
    this.clienteService.createCliente(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes'])
        swal.fire('Nuevo Cliente', `El Cliente: ${response.nombre} ${response.apellido} ha sido creado con exito.`, 'success')
      }, err => {
        this.errores = err.error.errors as string[];
        console.error('Código del error desde el back end: ' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.clienteService.getCliente(id).subscribe(cliente => this.cliente = cliente)
      }
    })
  }

  update(): void {
    this.clienteService.update(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes'])
        swal.fire('Cliente Actualizado', `${response.mensaje}: ${response.cliente.nombre} ${response.cliente.apellido}`, 'success')
      }, err => {
        this.errores = err.error.errors as string[];
        console.error('Código del error desde el back end: ' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  compararRegion(o1: Region, o2: Region): boolean {
    if(o1 === undefined && o2 === undefined){
      return true;
    }
    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false: o1.id === o2.id;
  }

}
