class CreateTaskUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :email
      t.string :crypted_password
      t.string :password_salt
      t.string :persistence_token

      add_index :users, :email, unique: true

      t.timestamps null: false
    end
  end
end
