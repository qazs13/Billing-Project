

var fi = document.getElementById( 'choose_file' );
//var infoArea = document.getElementById( 'file_name' );

fi.addEventListener( 'change', showFileName );

function showFileName( event ) {

            for (var i = 0; i <= fi.files.length - 1; i++) {

                var fname = fi.files.item(i).name;      // THE NAME OF THE FILE.

                document.getElementById('fp').innerHTML =
                    document.getElementById('fp').innerHTML + '<br /> ' +
                        fname ;

            }

}
 
