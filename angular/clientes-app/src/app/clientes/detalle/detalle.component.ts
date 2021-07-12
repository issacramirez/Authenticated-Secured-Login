import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'detalle-cliente',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {
  cliente!: Cliente;
  titulo: string = "Detalle de cliente";
  public imagenSeleccionada: File;
  constructor(private clienteService: ClienteService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let id: number = +params.get('id')!;
      if (id) {
        this.clienteService.getCliente(id).subscribe(cliente => {
          this.cliente = cliente;
        });
      }
    });
  }

  seleccionarFoto(event: any) {
    this.imagenSeleccionada = event.target.files[0];
    console.log(this.imagenSeleccionada);
    if(this.imagenSeleccionada.type.indexOf('image') < 0){
      Swal.fire('Error', 'Debe ser formato de imagen', 'error');
      this.imagenSeleccionada = null;
    }
  }

  subirFoto() {
    if (!this.imagenSeleccionada) {
      Swal.fire('Error', 'Debe Seleccionar una foto', 'error');
    } else {
      this.clienteService.subirFoto(this.imagenSeleccionada, this.cliente.id).subscribe(cliente => {
        this.cliente = cliente;
        Swal.fire('La foto se ha subido correctamente', `La foto: ${this.cliente.foto} subida con exito`, 'success');
      });
    }
  }

}
