
/** ----------------------------------------------------------------------------------------------------
* 
*	This code was automatically generated by the LeanFT Application Model code generator.
*
*	Changes to this file may cause incorrect behavior and will be lost 
*	when the code is regenerated.
*
*   ----------------------------------------------------------------------------------------------------
*/
package com.lftrepo.repo.SharedRepository.trim;

import com.hp.lft.sdk.*;

// This class is automatically generated by the LeanFT Application Model code generator - version 14.03
public class ObjectRepo_Trim extends AppModelBase {		private loginToSouthwestWindow loginToSouthwestWindow;

	public ObjectRepo_Trim() throws GeneralLeanFtException 
	{
		setName("ObjectRepo_Trim");
			loginToSouthwestWindow = new loginToSouthwestWindow(this);
		rebuildDescriptions();
	}
	
	public ObjectRepo_Trim(TestObject contextTestObject) throws GeneralLeanFtException
	{
		setName("ObjectRepo_Trim");
				loginToSouthwestWindow = new loginToSouthwestWindow(contextTestObject, this);
		rebuildDescriptions();
	}	

			public loginToSouthwestWindow loginToSouthwestWindow() { return loginToSouthwestWindow; }

		public class loginToSouthwestWindow extends WindowNodeBase
	{

		
			private txtUserIDEditField txtUserIDEditField;
	private txtPasswordEditField txtPasswordEditField;
	private btnLoginButton btnLoginButton;
						public loginToSouthwestWindow(AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(applicationModel);

					txtUserIDEditField = new txtUserIDEditField(this, applicationModel);
		txtPasswordEditField = new txtPasswordEditField(this, applicationModel);
		btnLoginButton = new btnLoginButton(this, applicationModel);
			
			setDisplayName("Login to Southwest");
		}
				public loginToSouthwestWindow(TestObject parent, AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(parent, applicationModel);

					txtUserIDEditField = new txtUserIDEditField(this, applicationModel);
		txtPasswordEditField = new txtPasswordEditField(this, applicationModel);
		btnLoginButton = new btnLoginButton(this, applicationModel);

			setDisplayName("Login to Southwest");
		}

		@Override
		protected com.hp.lft.sdk.winforms.WindowDescription createDescription() throws GeneralLeanFtException{
			com.hp.lft.sdk.winforms.WindowDescription description = null; 
			try{
				description = new com.hp.lft.sdk.winforms.WindowDescription.Builder().childWindow(false).ownedWindow(false).objectName("frmLogin").windowTitleRegExp("Login to Southwest").build();
			}catch(Exception e){
				throw new GeneralLeanFtException(e.getMessage(), e);
			}
			return description;
		}

				public txtUserIDEditField txtUserIDEditField() { return txtUserIDEditField; }
		public txtPasswordEditField txtPasswordEditField() { return txtPasswordEditField; }
		public btnLoginButton btnLoginButton() { return btnLoginButton; }
		
			public class txtUserIDEditField extends EditFieldNodeBase
	{

		
								public txtUserIDEditField(TestObject parent, AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(parent, applicationModel);

			
			setDisplayName("txtUserID");
		}

		@Override
		protected com.hp.lft.sdk.winforms.EditFieldDescription createDescription() throws GeneralLeanFtException{
			com.hp.lft.sdk.winforms.EditFieldDescription description = null; 
			try{
				description = new com.hp.lft.sdk.winforms.EditFieldDescription.Builder().objectName("txtUserID").build();
			}catch(Exception e){
				throw new GeneralLeanFtException(e.getMessage(), e);
			}
			return description;
		}

				
			}

	public class txtPasswordEditField extends EditFieldNodeBase
	{

		
								public txtPasswordEditField(TestObject parent, AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(parent, applicationModel);

			
			setDisplayName("txtPassword");
		}

		@Override
		protected com.hp.lft.sdk.winforms.EditFieldDescription createDescription() throws GeneralLeanFtException{
			com.hp.lft.sdk.winforms.EditFieldDescription description = null; 
			try{
				description = new com.hp.lft.sdk.winforms.EditFieldDescription.Builder().objectName("txtPassword").build();
			}catch(Exception e){
				throw new GeneralLeanFtException(e.getMessage(), e);
			}
			return description;
		}

				
			}

	public class btnLoginButton extends ButtonNodeBase
	{

		
								public btnLoginButton(TestObject parent, AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(parent, applicationModel);

			
			setDisplayName("Login");
		}

		@Override
		protected com.hp.lft.sdk.winforms.ButtonDescription createDescription() throws GeneralLeanFtException{
			com.hp.lft.sdk.winforms.ButtonDescription description = null; 
			try{
				description = new com.hp.lft.sdk.winforms.ButtonDescription.Builder().objectName("btnOK").build();
			}catch(Exception e){
				throw new GeneralLeanFtException(e.getMessage(), e);
			}
			return description;
		}

				
			}

	}


			
	public abstract class WindowNodeBase extends TopLevelObjectNodeBase<com.hp.lft.sdk.winforms.Window, com.hp.lft.sdk.winforms.WindowDescription> implements com.hp.lft.sdk.winforms.Window
	{
		public WindowNodeBase(AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(applicationModel);
		}

		public WindowNodeBase(TestObject parent, AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(parent, applicationModel);
		}

		


		@Override 
		public void activate() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().activate();
		}

		@Override 
		public void activate(com.hp.lft.sdk.MouseButton arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().activate(arg0);
		}

		@Override 
		public void click() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click();
		}

		@Override 
		public void click(com.hp.lft.sdk.ClickArgs arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click(arg0);
		}

		@Override 
		public void click(com.hp.lft.sdk.MouseButton arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click(arg0);
		}

		@Override 
		public void close() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().close();
		}

		@Override 
		public <TChild extends TestObject> TChild describe(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().describe(arg0, arg1);
		}

		@Override 
		public void doubleClick() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick();
		}

		@Override 
		public void doubleClick(com.hp.lft.sdk.ClickArgs arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick(arg0);
		}

		@Override 
		public void doubleClick(com.hp.lft.sdk.MouseButton arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick(arg0);
		}

		@Override 
		public void dragAndDropOn(com.hp.lft.sdk.SupportDragAndDrop arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().dragAndDropOn(arg0);
		}

		@Override 
		public void dragAndDropOn(com.hp.lft.sdk.SupportDragAndDrop arg0, com.hp.lft.sdk.DragAndDropArgs arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().dragAndDropOn(arg0, arg1);
		}

		@Override 
		public boolean exists() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().exists();
		}

		@Override 
		public boolean exists(java.lang.Integer arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().exists(arg0);
		}

		@Override 
		public <TChild extends TestObject> TChild[] findChildren(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException, java.lang.CloneNotSupportedException 
		{
			return getConcrete().findChildren(arg0, arg1);
		}

		@Override 
		public void fireEvent(java.lang.String arg0, java.lang.Object... arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().fireEvent(arg0, arg1);
		}

		@Override 
		public java.awt.Point getAbsoluteLocation() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getAbsoluteLocation();
		}

		@Override 
		public java.lang.String getDisplayName()  
		{
			return getConcrete().getDisplayName();
		}

		@Override 
		public java.lang.String getFullNamePath() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getFullNamePath();
		}

		@Override 
		public java.lang.String getFullType() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getFullType();
		}

		@Override 
		public int getHandle() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getHandle();
		}

		@Override 
		public java.awt.Point getLocation() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getLocation();
		}

		@Override 
		public java.lang.String getNativeClass() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getNativeClass();
		}

		@Override 
		public com.hp.lft.sdk.NativeObject getNativeObject() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getNativeObject();
		}

		@Override 
		public java.lang.String getObjectName() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getObjectName();
		}

		@Override 
		public <TValue> TValue getObjectProperty(java.lang.String arg0, java.lang.Class<TValue> arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getObjectProperty(arg0, arg1);
		}

		@Override 
		public java.awt.Dimension getSize() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getSize();
		}

		@Override 
		public java.awt.image.RenderedImage getSnapshot() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getSnapshot();
		}

		@Override 
		public java.lang.String getText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getText();
		}

		@Override 
		public java.awt.Rectangle[] getTextLocations(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getTextLocations(arg0);
		}

		@Override 
		public java.awt.Rectangle[] getTextLocations(java.lang.String arg0, java.awt.Rectangle arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getTextLocations(arg0, arg1);
		}

		@Override 
		public java.lang.String getVisibleText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getVisibleText();
		}

		@Override 
		public java.lang.String getVisibleText(java.awt.Rectangle arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getVisibleText(arg0);
		}

		@Override 
		public java.lang.String getWindowClassRegExp() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowClassRegExp();
		}

		@Override 
		public int getWindowId() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowId();
		}

		@Override 
		public com.hp.lft.sdk.WindowState getWindowState() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowState();
		}

		@Override 
		public java.lang.String getWindowTitleRegExp() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowTitleRegExp();
		}

		@Override 
		public boolean hasBorder() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().hasBorder();
		}

		@Override 
		public boolean hasCaption() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().hasCaption();
		}

		@Override 
		public boolean hasSizebox() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().hasSizebox();
		}

		@Override 
		public boolean hasSystemMenu() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().hasSystemMenu();
		}

		@Override 
		public void highlight() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().highlight();
		}

		@Override 
		public <TChild extends TestObject> int highlightMatches(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException, java.lang.CloneNotSupportedException 
		{
			return getConcrete().highlightMatches(arg0, arg1);
		}

		@Override 
		public boolean isActive() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isActive();
		}

		@Override 
		public boolean isChildWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isChildWindow();
		}

		@Override 
		public boolean isEnabled() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isEnabled();
		}

		@Override 
		public boolean isFocused() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isFocused();
		}

		@Override 
		public boolean isMaximizable() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isMaximizable();
		}

		@Override 
		public boolean isMdiChildWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isMdiChildWindow();
		}

		@Override 
		public boolean isMinimizable() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isMinimizable();
		}

		@Override 
		public boolean isOwnedWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isOwnedWindow();
		}

		@Override 
		public boolean isPopupWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isPopupWindow();
		}

		@Override 
		public boolean isToolWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isToolWindow();
		}

		@Override 
		public boolean isTopmost() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isTopmost();
		}

		@Override 
		public boolean isVisible() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isVisible();
		}

		@Override 
		public void maximize() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().maximize();
		}

		@Override 
		public void minimize() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().minimize();
		}

		@Override 
		public void mouseMove(com.hp.lft.sdk.Location arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().mouseMove(arg0);
		}

		@Override 
		public void move(java.awt.Point arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().move(arg0);
		}

		@Override 
		public void move(int arg0, int arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().move(arg0, arg1);
		}

		@Override 
		public void resize(java.awt.Dimension arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().resize(arg0);
		}

		@Override 
		public void resize(int arg0, int arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().resize(arg0, arg1);
		}

		@Override 
		public void restore() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().restore();
		}

		@Override 
		public void sendKeys(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().sendKeys(arg0);
		}

		@Override 
		public void sendKeys(java.lang.String arg0, java.util.EnumSet<com.hp.lft.sdk.KeyModifier> arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().sendKeys(arg0, arg1);
		}

		@Override 
		public void setDisplayName(java.lang.String arg0)  
		{
			getConcrete().setDisplayName(arg0);
		}

		@Override 
		public java.awt.Point verifyImageExists(java.awt.image.RenderedImage arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageExists(arg0);
		}

		@Override 
		public java.awt.Point verifyImageExists(java.awt.image.RenderedImage arg0, byte arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageExists(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, byte arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, byte arg1, byte arg2) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1, byte arg2) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1, byte arg2, byte arg3) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2, arg3);
		}
	}
	
	public abstract class EditFieldNodeBase extends AppModelNodeBase<com.hp.lft.sdk.winforms.EditField, com.hp.lft.sdk.winforms.EditFieldDescription> implements com.hp.lft.sdk.winforms.EditField
	{		
		public EditFieldNodeBase(TestObject parent, AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(parent, applicationModel);
		}

		


		@Override 
		public void click() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click();
		}

		@Override 
		public void click(com.hp.lft.sdk.ClickArgs arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click(arg0);
		}

		@Override 
		public void click(com.hp.lft.sdk.MouseButton arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click(arg0);
		}

		@Override 
		public <TChild extends TestObject> TChild describe(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().describe(arg0, arg1);
		}

		@Override 
		public void doubleClick() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick();
		}

		@Override 
		public void doubleClick(com.hp.lft.sdk.ClickArgs arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick(arg0);
		}

		@Override 
		public void doubleClick(com.hp.lft.sdk.MouseButton arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick(arg0);
		}

		@Override 
		public void dragAndDropOn(com.hp.lft.sdk.SupportDragAndDrop arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().dragAndDropOn(arg0);
		}

		@Override 
		public void dragAndDropOn(com.hp.lft.sdk.SupportDragAndDrop arg0, com.hp.lft.sdk.DragAndDropArgs arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().dragAndDropOn(arg0, arg1);
		}

		@Override 
		public boolean exists() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().exists();
		}

		@Override 
		public boolean exists(java.lang.Integer arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().exists(arg0);
		}

		@Override 
		public <TChild extends TestObject> TChild[] findChildren(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException, java.lang.CloneNotSupportedException 
		{
			return getConcrete().findChildren(arg0, arg1);
		}

		@Override 
		public void fireEvent(java.lang.String arg0, java.lang.Object... arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().fireEvent(arg0, arg1);
		}

		@Override 
		public java.awt.Point getAbsoluteLocation() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getAbsoluteLocation();
		}

		@Override 
		public java.lang.String getDisplayName()  
		{
			return getConcrete().getDisplayName();
		}

		@Override 
		public java.lang.String getErrorText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getErrorText();
		}

		@Override 
		public java.lang.String getFullNamePath() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getFullNamePath();
		}

		@Override 
		public java.lang.String getFullType() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getFullType();
		}

		@Override 
		public int getHandle() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getHandle();
		}

		@Override 
		public java.awt.Point getLocation() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getLocation();
		}

		@Override 
		public java.lang.String getNativeClass() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getNativeClass();
		}

		@Override 
		public com.hp.lft.sdk.NativeObject getNativeObject() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getNativeObject();
		}

		@Override 
		public java.lang.String getObjectName() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getObjectName();
		}

		@Override 
		public <TValue> TValue getObjectProperty(java.lang.String arg0, java.lang.Class<TValue> arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getObjectProperty(arg0, arg1);
		}

		@Override 
		public java.awt.Dimension getSize() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getSize();
		}

		@Override 
		public java.awt.image.RenderedImage getSnapshot() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getSnapshot();
		}

		@Override 
		public java.lang.String getText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getText();
		}

		@Override 
		public java.awt.Rectangle[] getTextLocations(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getTextLocations(arg0);
		}

		@Override 
		public java.awt.Rectangle[] getTextLocations(java.lang.String arg0, java.awt.Rectangle arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getTextLocations(arg0, arg1);
		}

		@Override 
		public java.lang.String getVisibleText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getVisibleText();
		}

		@Override 
		public java.lang.String getVisibleText(java.awt.Rectangle arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getVisibleText(arg0);
		}

		@Override 
		public java.lang.String getWindowClassRegExp() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowClassRegExp();
		}

		@Override 
		public int getWindowId() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowId();
		}

		@Override 
		public java.lang.String getWindowTitleRegExp() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowTitleRegExp();
		}

		@Override 
		public void highlight() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().highlight();
		}

		@Override 
		public <TChild extends TestObject> int highlightMatches(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException, java.lang.CloneNotSupportedException 
		{
			return getConcrete().highlightMatches(arg0, arg1);
		}

		@Override 
		public boolean isChildWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isChildWindow();
		}

		@Override 
		public boolean isEnabled() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isEnabled();
		}

		@Override 
		public boolean isFocused() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isFocused();
		}

		@Override 
		public boolean isOwnedWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isOwnedWindow();
		}

		@Override 
		public boolean isVisible() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isVisible();
		}

		@Override 
		public void mouseMove(com.hp.lft.sdk.Location arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().mouseMove(arg0);
		}

		@Override 
		public void select(int arg0, int arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().select(arg0, arg1);
		}

		@Override 
		public void sendKeys(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().sendKeys(arg0);
		}

		@Override 
		public void sendKeys(java.lang.String arg0, java.util.EnumSet<com.hp.lft.sdk.KeyModifier> arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().sendKeys(arg0, arg1);
		}

		@Override 
		public void setDisplayName(java.lang.String arg0)  
		{
			getConcrete().setDisplayName(arg0);
		}

		@Override 
		public void setSecure(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().setSecure(arg0);
		}

		@Override 
		public void setText(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().setText(arg0);
		}

		@Override 
		public java.awt.Point verifyImageExists(java.awt.image.RenderedImage arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageExists(arg0);
		}

		@Override 
		public java.awt.Point verifyImageExists(java.awt.image.RenderedImage arg0, byte arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageExists(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, byte arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, byte arg1, byte arg2) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1, byte arg2) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1, byte arg2, byte arg3) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2, arg3);
		}
	}
	
	public abstract class ButtonNodeBase extends AppModelNodeBase<com.hp.lft.sdk.winforms.Button, com.hp.lft.sdk.winforms.ButtonDescription> implements com.hp.lft.sdk.winforms.Button
	{		
		public ButtonNodeBase(TestObject parent, AppModelBase applicationModel) throws GeneralLeanFtException
		{
			super(parent, applicationModel);
		}

		


		@Override 
		public void click() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click();
		}

		@Override 
		public void click(com.hp.lft.sdk.ClickArgs arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click(arg0);
		}

		@Override 
		public void click(com.hp.lft.sdk.MouseButton arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().click(arg0);
		}

		@Override 
		public <TChild extends TestObject> TChild describe(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().describe(arg0, arg1);
		}

		@Override 
		public void doubleClick() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick();
		}

		@Override 
		public void doubleClick(com.hp.lft.sdk.ClickArgs arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick(arg0);
		}

		@Override 
		public void doubleClick(com.hp.lft.sdk.MouseButton arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().doubleClick(arg0);
		}

		@Override 
		public void dragAndDropOn(com.hp.lft.sdk.SupportDragAndDrop arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().dragAndDropOn(arg0);
		}

		@Override 
		public void dragAndDropOn(com.hp.lft.sdk.SupportDragAndDrop arg0, com.hp.lft.sdk.DragAndDropArgs arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().dragAndDropOn(arg0, arg1);
		}

		@Override 
		public boolean exists() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().exists();
		}

		@Override 
		public boolean exists(java.lang.Integer arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().exists(arg0);
		}

		@Override 
		public <TChild extends TestObject> TChild[] findChildren(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException, java.lang.CloneNotSupportedException 
		{
			return getConcrete().findChildren(arg0, arg1);
		}

		@Override 
		public void fireEvent(java.lang.String arg0, java.lang.Object... arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().fireEvent(arg0, arg1);
		}

		@Override 
		public java.awt.Point getAbsoluteLocation() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getAbsoluteLocation();
		}

		@Override 
		public java.lang.String getDisplayName()  
		{
			return getConcrete().getDisplayName();
		}

		@Override 
		public java.lang.String getErrorText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getErrorText();
		}

		@Override 
		public java.lang.String getFullNamePath() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getFullNamePath();
		}

		@Override 
		public java.lang.String getFullType() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getFullType();
		}

		@Override 
		public int getHandle() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getHandle();
		}

		@Override 
		public java.awt.Point getLocation() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getLocation();
		}

		@Override 
		public java.lang.String getNativeClass() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getNativeClass();
		}

		@Override 
		public com.hp.lft.sdk.NativeObject getNativeObject() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getNativeObject();
		}

		@Override 
		public java.lang.String getObjectName() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getObjectName();
		}

		@Override 
		public <TValue> TValue getObjectProperty(java.lang.String arg0, java.lang.Class<TValue> arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getObjectProperty(arg0, arg1);
		}

		@Override 
		public java.awt.Dimension getSize() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getSize();
		}

		@Override 
		public java.awt.image.RenderedImage getSnapshot() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getSnapshot();
		}

		@Override 
		public java.lang.String getText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getText();
		}

		@Override 
		public java.awt.Rectangle[] getTextLocations(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getTextLocations(arg0);
		}

		@Override 
		public java.awt.Rectangle[] getTextLocations(java.lang.String arg0, java.awt.Rectangle arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getTextLocations(arg0, arg1);
		}

		@Override 
		public java.lang.String getVisibleText() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getVisibleText();
		}

		@Override 
		public java.lang.String getVisibleText(java.awt.Rectangle arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getVisibleText(arg0);
		}

		@Override 
		public java.lang.String getWindowClassRegExp() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowClassRegExp();
		}

		@Override 
		public int getWindowId() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowId();
		}

		@Override 
		public java.lang.String getWindowTitleRegExp() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().getWindowTitleRegExp();
		}

		@Override 
		public void highlight() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().highlight();
		}

		@Override 
		public <TChild extends TestObject> int highlightMatches(java.lang.Class<TChild> arg0, com.hp.lft.sdk.Description arg1) throws com.hp.lft.sdk.GeneralLeanFtException, java.lang.CloneNotSupportedException 
		{
			return getConcrete().highlightMatches(arg0, arg1);
		}

		@Override 
		public boolean isChildWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isChildWindow();
		}

		@Override 
		public boolean isEnabled() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isEnabled();
		}

		@Override 
		public boolean isFocused() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isFocused();
		}

		@Override 
		public boolean isOwnedWindow() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isOwnedWindow();
		}

		@Override 
		public boolean isVisible() throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().isVisible();
		}

		@Override 
		public void mouseMove(com.hp.lft.sdk.Location arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().mouseMove(arg0);
		}

		@Override 
		public void sendKeys(java.lang.String arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().sendKeys(arg0);
		}

		@Override 
		public void sendKeys(java.lang.String arg0, java.util.EnumSet<com.hp.lft.sdk.KeyModifier> arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			getConcrete().sendKeys(arg0, arg1);
		}

		@Override 
		public void setDisplayName(java.lang.String arg0)  
		{
			getConcrete().setDisplayName(arg0);
		}

		@Override 
		public java.awt.Point verifyImageExists(java.awt.image.RenderedImage arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageExists(arg0);
		}

		@Override 
		public java.awt.Point verifyImageExists(java.awt.image.RenderedImage arg0, byte arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageExists(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, byte arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, byte arg1, byte arg2) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1, byte arg2) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2);
		}

		@Override 
		public boolean verifyImageMatch(java.awt.image.RenderedImage arg0, com.hp.lft.sdk.ImageMaskArea arg1, byte arg2, byte arg3) throws com.hp.lft.sdk.GeneralLeanFtException 
		{
			return getConcrete().verifyImageMatch(arg0, arg1, arg2, arg3);
		}
	}
}
