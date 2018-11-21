package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Album;
import model.Photo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Photos;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class albumController implements Initializable {
	private Photos application;
	private String comuser;
	private String comalbum;
	
	public String getComuser() {
		return comuser;
	}

	public void setComuser(String comuser) {
		this.comuser = comuser;
	}

	public String getComalbum() {
		return comalbum;
	}

	public void setComalbum(String comalbum) {
		this.comalbum = comalbum;
	}
	
	@FXML
	private Label albumname;
	@FXML
	private Button returnuser;
	@FXML
	private Button logout;
	@FXML
	private TableView<Photo> photodisplay = new TableView<>();
	
	@FXML
	private TableColumn <Photo, Image> picviewcol;
	
	@FXML
	private TableColumn <Photo, String> photonamecol;

	@FXML
	private TableColumn <Photo, Long> photodatecol;
	
	private ObservableList<Photo> photolist;
	

	// Event Listener on Button.onAction
	@FXML
	public void returnusersys(ActionEvent event) {
		application.gotouser(comuser);
	}
	
	public void setApp(Photos application){
        this.application = application;
    }
    
	@FXML
    private void logout(ActionEvent event) {
       application.gotologin();
    }
	
	
	public void settitle() {
		albumname.setText(comalbum);
	}
	
	//testing list	
		static List<Photo> testlistb = new ArrayList<>();	
		static {
			testlistb.add(new Photo("photo1", "black", 20181119));
			testlistb.add(new Photo("photo2", "white", 20181119));
			}
		
	
	
	public void showPhoto(List<Photo> viewlist) {		
		//evoke controller function to get a list of all user
		photolist = FXCollections.observableArrayList(viewlist);
		
		//set thumbnail image
		picviewcol.setCellFactory(param -> {
		       //Set up the ImageView
		       final ImageView imageview = new ImageView();
		       imageview.setFitHeight(40);
		       imageview.setFitWidth(40);

		       //Set up the Table
		       TableCell<Photo, Image> cell = new TableCell<Photo, Image>() {
		           public void updateItem(Image item, boolean empty) {
		             if (item != null) {
		                  imageview.setImage(item);
		             }
		           }
		        };
		        // Attach the imageview to the cell
		        cell.setGraphic(imageview);
		        return cell;
		   });
		
		picviewcol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getImageview(cellData.getValue().getPhotoFileName())));
		//picviewcol.setCellValueFactory(new PropertyValueFactory<Photo, Image>("photoview"));
				
		photonamecol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPhotoFileName()));
		photodatecol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getDateOfPhoto()));
		photodisplay.setItems(photolist);	   
	   }
	
	//double clicked a photo to view it in a new page
		public void photoclick() {
			photodisplay.setOnMouseClicked(new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent click) {
			        if (click.getClickCount() == 2) {
			        	int photoIndex = photodisplay.getSelectionModel().getSelectedIndex();
			        	String selectedphoto = testlistb.get(photoIndex).getPhotoFileName();
			        	//get the album name of which the selected photo belongs to
			        	String photocomalbum = testlistb.get(photoIndex).getPhotoFileName();
			        	application.gotophotos(comuser, photocomalbum, selectedphoto);			        				           
			        }
			    }
			});		
		}

	// right click to show menu of adding, copying and moving photo
	public void photorightclick() {
		//testing
		List<Album> testlist = new ArrayList<>();	           	
    	testlist.add(new Album("album1"));
    	testlist.add(new Album("album2"));
		
    	//set up parent menu content 	
    	ContextMenu photomenu = new ContextMenu();
    	MenuItem addphoto = new MenuItem("Add photo");
    	MenuItem deletephoto = new MenuItem("Delete photo");
		Menu movephoto = new Menu("Move to");
		Menu copyphoto = new Menu("Copy to");		
		
		
		//handle right click event
		
		photodisplay.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent  me) ->  {
		    if (me.getButton() == MouseButton.SECONDARY )  {
		    	int photoIndex = photodisplay.getSelectionModel().getSelectedIndex();
		    			    		   
				//handle add envent
				addphoto.setOnAction(new EventHandler<ActionEvent>() 
				{
				    @Override
				    public void handle(ActionEvent event)
				    {
				        System.out.println("waiting for add function");
				        //update the photo list after adding
				        //showPhoto();
				        
				    }
				});
				
				//handle delete envent
				deletephoto.setOnAction(new EventHandler<ActionEvent>() 
				{
				    @Override
				    public void handle(ActionEvent event)
				    {
				        System.out.println("waiting for add function");
				        //update the photo list after adding
				        //showPhoto();
				        
				    }
				});

				
				//set up children menu content and handle children menu event--moving and copying
				movephoto.getItems().clear();
				copyphoto.getItems().clear();
				for (int i =0; i< testlist.size(); i++) {
					String albumname = testlist.get(i).getAlbumName();
					MenuItem movingchoice  = new MenuItem(albumname);
					MenuItem copyingchoice  = new MenuItem(albumname);			
					movephoto.getItems().add(movingchoice);
					copyphoto.getItems().add(copyingchoice);
					movingchoice.setOnAction(new EventHandler<ActionEvent>() 
					{
					    @Override
					    public void handle(ActionEvent event)
					    {
					        System.out.println("moving to " + albumname);
					        //update the photo list after moving
					        //showPhoto();			        
					    }
					});
					
					copyingchoice.setOnAction(new EventHandler<ActionEvent>() 
					{
					    @Override
					    public void handle(ActionEvent event)
					    {
					        System.out.println("copying to " + albumname);			            
					    }
					});
					
					photomenu.getItems().addAll(addphoto, movephoto, copyphoto);
					photomenu.show(photodisplay, me.getScreenX(), me.getScreenY());
					
				}
								
		    }  else  {
		    	photomenu.hide();
		    }
		});			

	}

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }
}
