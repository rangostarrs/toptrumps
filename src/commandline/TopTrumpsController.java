package commandline;

public class TopTrumpsController {
	
	private TopTrumpsModel ContrModel;
	private TopTrumpsView  ContrView;
	
	TopTrumpsController(TopTrumpsModel model, TopTrumpsView view) {
		ContrModel = model;
		ContrView  = view;
	}
}