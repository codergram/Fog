$(document).ready(function () {

    //#selectAllBoxes er den id vi gav vores <th><input id="selectAllBoxes" type="checkbox"></th>
    $('#selectAllBoxes').click(function (event) {

        if (this.checked) {

            //' .checkBoxes' er den klasse i satte i echo "<td><input class='checkBoxes' type='checkbox' name='checkboxArray[]' value='{$post_id}'></td>";
            $('.checkBoxes').each(function () {

                //Sætter alle checkbokse med .checkBoxes til true
                this.checked = true;

            });

        } else {

            //' .checkBoxes' er den klasse i satte i echo "<td><input class='checkBoxes' type='checkbox' name='checkboxArray[]' value='{$post_id}'></td>";
            $('.checkBoxes').each(function () {

                //Sætter alle checkbokse med .checkBoxes til false
                this.checked = false;

            });
        }
    });
});