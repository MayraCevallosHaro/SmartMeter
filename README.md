
# SmartMeter
SmartMeter un proyecto de Smart orientado al monitoreo de energía de los hogares <br />
- Al iniciar el proyecto se presenta un Splash Screen en el cual demora unos segundos <br />
-  El proyecto maneja 2 roles, el administrador y el usuario, en la que el administrador es quien ingresa los dispositivos y los asigna a los usuarios,
en la cual 1 diapositivo solo puede tener un dispositivo.<br />
-  Al ingresar correctamente los datos aparece la pantalla principal en la que internamente controla el ingreso si es "adm" aparecen otras opciones y si el es "usuario" aparece las opciones como la de Ver el consumo de los usuarios, la de reportar fallo de energía, agregar dispositivos, y la opcion de modificar los datos si el usuario lo desea.
- Si ingresa como usuario, tendrá opciones como la de ver el consumo de energía la cantidad de vatios contabilizados hasta ese momento, dado que mientras este conectado el dispositivo, se ingresarán datos que seguiran contabilizado, dado que envia datos cada 5 segundos. Los datos son enviados siempre y cuando el usuario tenga energía electrica si el usuario no tiene, se enviará un Whatsapp al encargado de la empresa eléctrica reportando dicho fallo, en caso que el usuario no tenga datos disponibles se enviará la notificación mediante mensaje de texto.
