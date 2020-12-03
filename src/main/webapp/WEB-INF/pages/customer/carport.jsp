<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <h2 class="mt-4 mb-4 text-center">Sammensæt din helt egen carport herunder</h2>
        <br>
        <form class="form-carport" action="Confirmation" method="GET">
            <div class="form-group">
                <label for="length">Carportens længde</label>
                <select class="form-control" id="length" name="length" required>
                    <option value="580">580</option>
                    <option value="590">590</option>
                    <option value="600">600</option>
                    <option value="610">610</option>
                    <option value="620">620</option>
                    <option value="630">630</option>
                    <option value="640">640</option>
                    <option value="650">650</option>
                    <option value="660">660</option>
                    <option value="670">670</option>
                    <option value="680">680</option>
                    <option value="690">690</option>
                    <option value="700">700</option>
                    <option value="710">710</option>
                    <option value="720">720</option>
                    <option value="730">730</option>
                    <option value="740">740</option>
                    <option value="750">750</option>
                    <option value="760">760</option>
                    <option value="770">770</option>
                    <option value="780">780</option>
                </select>
            </div>

            <div class="form-group">
                <label for="width">Carportens bredde</label>
                <select class="form-control" id="width" name="width" required>
                    <option value="300">300</option>
                    <option value="310">310</option>
                    <option value="320">320</option>
                    <option value="330">330</option>
                    <option value="340">340</option>
                    <option value="350">350</option>
                    <option value="360">360</option>
                    <option value="370">370</option>
                    <option value="380">380</option>
                    <option value="390">390</option>
                    <option value="400">400</option>
                    <option value="410">410</option>
                    <option value="420">420</option>
                    <option value="430">430</option>
                    <option value="440">440</option>
                    <option value="450">450</option>
                    <option value="460">460</option>
                    <option value="470">470</option>
                    <option value="480">480</option>
                    <option value="490">490</option>
                    <option value="500">500</option>
                    <option value="510">510</option>
                    <option value="520">520</option>
                    <option value="530">530</option>
                    <option value="540">540</option>
                    <option value="550">550</option>
                    <option value="560">560</option>
                    <option value="570">570</option>
                    <option value="580">580</option>
                    <option value="590">590</option>
                    <option value="600">600</option>
                </select>
            </div>

            <div class="form-group">
                <label for="roof">Carportens tagtype</label>
                <select class="form-control" id="roof" name="roof" required>
                    <option value="flat">Fladt</option>
                    <option value="peak">Med 15 graders hældning</option>
                </select>
            </div>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="shedOption" name="shedOption" onchange="document.getElementById('shedSize').disabled = !this.checked;">
                <label class="custom-control-label" for="shedOption">Jeg ønsker et skur</label>
            </div>
            <div class="form-group">
                <label for="shedSize">Skur type</label>
                <select class="form-control" id="shedSize" name="shedSize" disabled>
                    <option value="whole">Hel bredde</option>
                    <option value="half">Halv bredde</option>
                </select>
            </div>

            <div style="text-align: center">
                <button type="submit" class="btn btn-primary">Se forespørgelse</button>
            </div>
        </form>
    </div>
    <div class="col-md-2"></div>
</div>


