#include <iostream>
#include <string>
using namespace std;

class Estudiante {

   private:
     string nombre;
   private:
     double nota1;
   private:
     double nota2;
   private:
     double nota3;
   private:
     double promedio;
   private:
     string estado;

   public:
     Estudiante() {}
   public:
    void asignar_datos(string nombre_param, double nota_param1, double nota_param2, double nota_param3) {
         this->nombre = nombre_param;
         this->nota1 = nota_param1;
         this->nota2 = nota_param2;
         this->nota3 = nota_param3;
         this->promedio = ((((nota_param1 + nota_param2) + nota_param3)) / 3);
         if ((   this->promedio >= 70)) {
            this->estado = "Aprobado";
      } else {
            this->estado = "Reprobado";
      }
    }
   public:
    void mostrar_info() {
         cout << "Estudiante:" <<    this->nombre << endl;
         cout << "Promedio:" <<    this->promedio << endl;
         cout << "Estado:" <<    this->estado << endl;
    }
   public:
    double obtener_promedio() {
      return    this->promedio;
    }
};

string evaluar_beca(double promedio) {
      if ((promedio > 80)) {
      return "Beca aprobada";
   } else {
      return "No aplica para beca";
   }
}

int main() {
   const int LIMITE = 3;
   Estudiante e1, e2, e3;
   int i = 1;
   int salir = 0;
   do {
        i = 1;
        while ((i <= LIMITE)) {
        string nombre;
        double b, c, d;
        cout << "Ingrese el nombre del estudiante " << i << ":" << endl;
        cin >> nombre;
        cout << "Ingrese la primera nota:" << endl;
        cin >> b;
        cout << "Ingrese la segunda nota:" << endl;
        cin >> c;
        cout << "Ingrese la tercera nota:" << endl;
        cin >> d;
        if ((i == 1)) {
         e1.asignar_datos(nombre, b, c, d);
   } else {
         if ((i == 2)) {
               e2.asignar_datos(nombre, b, c, d);
         } else {
               e3.asignar_datos(nombre, b, c, d);
         }
   }
        i = (i + 1);
   }
        cout << "=== INFORMACIÓN DE ESTUDIANTES ===" << endl;
        for (int j = 1; (j <= LIMITE); j = (j + 1)) {
        if ((j == 1)) {
         e1.mostrar_info();
         cout << "Beca:" << evaluar_beca(   e1.obtener_promedio()) << endl;
   } else {
         if ((j == 2)) {
               e2.mostrar_info();
               cout << "Beca:" << evaluar_beca(   e2.obtener_promedio()) << endl;
         } else {
               e3.mostrar_info();
               cout << "Beca:" << evaluar_beca(   e3.obtener_promedio()) << endl;
         }
   }
        cout << "--------------------" << endl;
   }
        cout << "¿Desea salir? (1 = sí / 0 = no):" << endl;
        cin >> salir;
   } while ((salir != 1));
   return 0;
}
